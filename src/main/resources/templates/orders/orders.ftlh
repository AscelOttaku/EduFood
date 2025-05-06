<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/orders.css">
    <title>Orders</title>

</head>

<header>
    <#include "../layout/navbar.ftlh">
</header>

<body>
<div class="container pt-4">
    <h1>All Orders</h1>

    <#list orders as order>
        <hr>
        <h2>Order #${order.id}</h2>

        <h3>User Information</h3>
        <ul>
            <li><strong>Username:</strong> ${order.user.name}</li>
            <li><strong>Email:</strong> ${order.user.email}</li>
        </ul>

        <h3>Order Details</h3>
        <table>
            <thead>
            <tr>
                <th>Dish Name</th>
                <th>Restaurant</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>
            </thead>
            <tbody>
            <#list order.dishes as dish, quantity>
                <tr>
                    <td>${dish.name}</td>
                    <td>${dish.restaurant.name}</td>
                    <td>$${dish.price?string["0.00"]}</td>
                    <td>${quantity}</td>
                    <td>$${(dish.price * quantity)?string["0.00"]}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </#list>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>