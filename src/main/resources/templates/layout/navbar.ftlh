<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <div class="container">
        <a class="navbar-brand" href="#">
            <i class="bi bi-briefcase me-2"></i>JobSearch
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/restaurants">
                        <i class="bi bi-search me-1"></i> Restaurants
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/buckets">
                        <i class="bi bi-search me-1"></i> Bucket
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/orders/users">
                        <i class="bi bi-clock-history me-1"></i> Orders
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/users/profile">
                        <i class="bi bi-clock-history me-1"></i> Профиль
                    </a>
                </li>
            </ul>

            <div class="d-flex">
                <form class="d-flex me-3" action="/restaurants/name" method="get">
                    <div class="input-group">
                        <input class="form-control" type="search" name="restaurantName" placeholder="Поиск..."
                               aria-label="Search">
                        <button class="btn btn-outline-light" type="submit">
                            <i class="bi bi-search"></i>
                        </button>
                    </div>
                </form>

                <#if user??>
                    <div class="dropdown">
                        <a class="btn btn-outline-light dropdown-toggle" href="#" authority="button" id="userMenu"
                           data-bs-toggle="dropdown">
                            <i class="bi bi-person-circle me-1"></i> ${user.name!"Профиль"}
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="/users/profile"><i class="bi bi-person me-2"></i>Профиль</a>
                            </li>
                            <li><a class="dropdown-item" href="/users/responses"><i class="bi bi-envelope me-2"></i>Отклики</a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <#if _csrf??>
                                <form action="/logout" method="post">
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                                           value="${(_csrf.token)!'--no-token--'}"/>
                                    <button type="submit" class="btn btn-outline-info m-2"
                                            id="logout">Logout
                                    </button>
                                </form>
                            <#else>
                                <a href="/logout" class="btn btn-outline-info m-2"
                                   id="logout">Logout</a>
                            </#if>
                        </ul>
                    </div>
                <#else>
                    <#if !authorizedUser??>
                        <a class="btn btn-outline-light me-2" href="/auth/login">
                            <i class="bi bi-box-arrow-in-right me-1"></i> Login
                        </a>

                        <a class="btn btn-primary" href="/auth/registration">
                            <i class="bi bi-person-plus me-1"></i> Регистрация
                        </a>
                    <#else>
                        <a class="btn btn-outline-light me-2" href="/auth/logout">
                            <i class="bi bi-box-arrow-in-right me-1"></i> Logout
                        </a>
                    </#if>
                </#if>
            </div>
        </div>
    </div>
</nav>