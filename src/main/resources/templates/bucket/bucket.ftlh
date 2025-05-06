<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Корзина</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/bucket.css">
</head>
<body>
<#include "../layout/navbar.ftlh">

<div class="bucket-container">
    <h1 class="bucket-header">Корзина пользователя</h1>

    <#if bucketPage??>
        <div class="bucket-items">
            <#list bucketPage.content as item>
                <div class="bucket-item">
                    <div class="dish-row">
                        <span class="dish-name">${item.dish().name!''}</span>

                        <form action="/buckets/add/${item.dish().id}" method="post" style="display:inline;">
                            <#if _csrf??>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            </#if>
                            <button type="submit" class="btn btn-sm btn-success">+</button>
                        </form>

                        <span class="dish-quantity">x${item.quantity()}</span>

                        <form action="/buckets/remove/${item.dish().id}" method="post" style="display:inline;">
                            <#if _csrf??>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            </#if>
                            <button type="submit" class="btn btn-sm btn-danger">-</button>
                        </form>

                        <span class="dish-price">${item.dish().price!0} сом</span>
                        <span class="dish-total">= ${item.dish().price * item.quantity()} сом</span>
                    </div>
                    <input type="hidden" name="dishIds" value="${item.dish().id!''}">
                </div>
            </#list>



            <div class="total-price mt-3">
                <span class="total-label">Общая сумма:</span>
                <span class="total-value">${total!0} сом</span>
            </div>

            <form name="dishes" method="post" action="/orders" class="checkout-section mt-3">
                <button type="submit" class="btn btn-checkout">
                    <i class="bi bi-cart-check"></i> Оформить заказ
                </button>
            </form>

            <#if bucketPage.totalPages gt 1>
                <nav aria-label="Page navigation" class="mt-4">
                    <ul class="pagination justify-content-center">
                        <li class="page-item <#if !bucketPage.hasPreviousPage>disabled</#if>">
                            <a class="page-link" href="/buckets?page=${bucketPage.page - 1}">
                                &laquo; Назад
                            </a>
                        </li>

                        <#list 1..bucketPage.totalPages as pageNum>
                            <li class="page-item <#if bucketPage.page == pageNum - 1>active</#if>">
                                <a class="page-link" href="/buckets?page=${pageNum - 1}">${pageNum}</a>
                            </li>
                        </#list>

                        <li class="page-item <#if !bucketPage.hasNextPage>disabled</#if>">
                            <a class="page-link" href="/buckets?page=${bucketPage.page + 1}">
                                Вперед &raquo;
                            </a>
                        </li>
                    </ul>
                </nav>
            </#if>

        </div>
    <#else>
        <div class="empty-bucket">
            <p class="fs-4">Ваша корзина пуста</p>
            <p class="text-muted">Добавьте блюда из ресторанов</p>
        </div>
    </#if>



</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>