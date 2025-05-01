<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/static/css/profile.css">

    <title>Job Search | Профиль</title>
    <#include "../layout/navbar.ftlh">
</head>

<body class="bg-light">
<div class="profile-container py-4">
    <div class="profile-card">
        <div class="profile-header">
            <div class="d-flex justify-content-between align-items-center">
                <h1 class="profile-title">
                    <i class="bi bi-person-badge me-2"></i>Профиль ${userDto.accountType}
                </h1>
                <a href="/userDtos/update/profile" class="btn btn-outline-secondary">
                    <i class="bi bi-pencil-square me-2"></i>Редактировать
                </a>
            </div>
        </div>

        <div class="p-4">
            <div class="row mb-4">
                <div class="col-md-3 text-center mb-3">
                    <#if userDto.avatar??>
                        <img src="/userDtos/avatars" class="profile-avatar rounded-circle" alt="Avatar">
                    <#else>
                        <div class="profile-avatar rounded-circle bg-secondary d-flex align-items-center justify-content-center text-white mx-auto">
                            <i class="bi bi-person-fill fs-3"></i>
                        </div>
                    </#if>
                </div>
                <div class="col-md-9">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <div class="card h-100 info-card">
                                <div class="card-body">
                                    <h5 class="card-title text-muted mb-3">
                                        <i class="bi bi-person-lines-fill me-2"></i>Основная информация
                                    </h5>
                                    <div class="mb-2">
                                        <span class="fw-bold">Имя Фамилия:</span>
                                        <p class="mb-0 ps-3">${userDto.name} ${userDto.surname}</p>
                                    </div>
                                    <div class="mb-2">
                                        <span class="fw-bold">Возраст:</span>
                                        <p class="mb-0 ps-3">${userDtoDto.age}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card h-100 info-card">
                                <div class="card-body">
                                    <h5 class="card-title text-muted mb-3">
                                        <i class="bi bi-telephone-fill me-2"></i>Контакты
                                    </h5>
                                    <div class="mb-2">
                                        <span class="fw-bold">Email:</span>
                                        <p class="mb-0 ps-3">${userDto.email}</p>
                                    </div>
                                    <div>
                                        <span class="fw-bold">Телефон:</span>
                                        <p class="mb-0 ps-3">${userDto.phoneNumber}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>