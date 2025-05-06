<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
        <h2>Order ID: ${order.id}</h2>

        <h3>User Information</h3>
        <ul>
            <li><strong>Username:</strong> ${order.user.name}</li>
            <li><strong>Email:</strong> ${order.user.email}</li>
        </ul>

        <h3>Dishes</h3>
        <table border="1">
            <tr>
                <th>Dish Name</th>
                <th>Restaurant</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>
            <#list order.dishes as dish, quantity>
                <tr>
                    <td>${dish.name}</td>
                    <td>${dish.restaurant.name}</td>
                    <td>${dish.price}</td>
                    <td>${quantity}</td>
                    <td>${dish.price * quantity}</td>
                </tr>
            </#list>
        </table>
    </#list>
</div>
</body>
</html>
