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

    <#if bucket?? && bucket?size gt 0>
        <div class="bucket-items">
            <#list bucket as restaurant, dish>
                <div class="bucket-item">
                    <div class="restaurant-name">${restaurant.name}</div>
                    <div class="dish-row">
                        <span class="dish-name">${dish.name}</span>
                        <span class="dish-price">${dish.price} сом</span>
                    </div>
                </div>
            </#list>

            <div class="total-price">
                <span class="total-label">Общая сумма:</span>
                <span class="total-value">${totalPrice} сом</span>
            </div>

            <form name="dishes" method="post" action="orders" class="checkout-section">
                <input type="hidden" value="${bucket.values}">
                <button class="btn btn-checkout">
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