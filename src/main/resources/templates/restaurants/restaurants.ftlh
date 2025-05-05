<#-- restaurants-list.ftlh -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurants List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .restaurant-item {
            margin-bottom: 10px;
            border: 1px solid #dee2e6;
            border-radius: 5px;
            padding: 15px;
            background-color: #f8f9fa;
        }
        .restaurant-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            cursor: pointer;
        }
        .arrow {
            transition: transform 0.3s;
        }
        .arrow.down {
            transform: rotate(180deg);
        }
    </style>
</head>
<body>
<#include "../layout/navbar.ftlh">
<div class="container mt-5">
    <h1 class="mb-4 mt-3">Restaurants</h1>

    <#if restaurants??>
        <div class="restaurants-list">
            <#list restaurants as restaurant>
                <div class="restaurant-item">
                    <a href="/dishes/restaurants/${restaurant.id}" class="text-decoration-none text-dark">
                        <div class="restaurant-header">
                            <span>${restaurant.name!''}</span>
                        </div>
                    </a>
                </div>
            </#list>
        </div>
    <#else>
        <div class="alert alert-info">No restaurants found.</div>
    </#if>
</div>
</body>
</html>