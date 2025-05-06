<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .restaurant-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .dishes-container {
            margin-top: 15px;
            padding-top: 15px;
            border-top: 1px solid #eee;
        }
        .dish-item {
            padding: 10px 0;
        }
    </style>
</head>
<body>
<#include "../layout/navbar.ftlh">

<div class="container mt-5">
    <#if restaurant??>
        <div class="restaurant-header">
            <h1>${restaurant.name}</h1>
            <#if restaurant.id??>
                <p class="text-muted">ID: ${restaurant.id}</p>
            </#if>
        </div>

        <#if dishesPage??>
            <div class="dishes-container">
                <#if dishesPage.content?size gt 0>
                    <#list dishesPage.content as dish>
                        <div class="dish-item border-bottom pb-3 mb-3">
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <strong>Dish name: ${dish.name!''}</strong><br>
                                    <span class="text-success">$${dish.price?string["0.00"]}</span>
                                </div>
                                <form method="post" action="/buckets/${dish.id}">

                                    <#if _csrf??>
                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                               value="${(_csrf.token)!'csrf-token'}"/>
                                    </#if>

                                    <button type="submit" class="btn btn-sm btn-outline-primary">Добавить в корзину</button>
                                </form>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <div class="text-muted">No dishes available</div>
                </#if>
            </div>
        </#if>
    <#else>
        <div class="alert alert-danger">Restaurant not found.</div>
    </#if>

    <#if dishesPage.totalPages gt 1>
        <#assign restaurantId = (dishesPage.content[0].restaurant.id)!>

        <nav aria-label="Page navigation" class="mt-4">
            <ul class="pagination justify-content-center">
                <li class="page-item <#if !dishesPage.hasPreviousPage>disabled</#if>">
                    <a class="page-link" href="/dishes/restaurants/${restaurantId}?page=${dishesPage.page - 1}">
                        &laquo; Назад
                    </a>
                </li>

                <#list 1..dishesPage.totalPages as pageNum>
                    <li class="page-item <#if dishesPage.page == pageNum - 1>active</#if>">
                        <a class="page-link" href="/dishes/restaurants/${restaurantId}?page=${pageNum - 1}">${pageNum}</a>
                    </li>
                </#list>

                <li class="page-item <#if !dishesPage.hasNextPage>disabled</#if>">
                    <a class="page-link" href="/dishes/restaurants/${restaurantId}?page=${dishesPage.page + 1}">
                        Вперед &raquo;
                    </a>
                </li>
            </ul>
        </nav>
    </#if>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>