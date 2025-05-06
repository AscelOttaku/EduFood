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

    <#if bucket??>
        <div class="bucket-items">
            <#list bucket as dishDto, quantity>
                <div class="bucket-item">
                    <div class="dish-row">
                        <span class="dish-name">${dishDto.name!''}</span>
                        <span class="dish-quantity">x${quantity}</span>
                        <span class="dish-price">$${dishDto.price!0} </span>
                        <span class="dish-total">= $${dishDto.price * quantity} </span>
                    </div>

                    <form method="post" action="/buckets/delete/${dishDto.id}">

                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>

                        <input type="hidden" name="dishId" value="${dishDto.id!''}">
                        <button type="submit" class="btn btn-sm btn-outline-danger">-</button>
                    </form>

                    <form method="post" action="/buckets/${dishDto.id}">

                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>

                        <input type="hidden" name="dishId" value="${dishDto.id!''}">
                        <button type="submit" class="btn btn-sm btn-outline-success">+</button>
                    </form>
                </div>
            </#list>

            <div class="total-price mt-3">
                <span class="total-label">Общая сумма:</span>
                <span class="total-value">$${total!0}</span>
            </div>

            <form name="dishes" method="post" action="orders" class="checkout-section mt-3">

                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <button type="submit" class="btn btn-checkout">
                    <i class="bi bi-cart-check"></i> Оформить заказ
                </button>
            </form>
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