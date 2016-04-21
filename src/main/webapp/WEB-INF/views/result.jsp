<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<p>сумма всех заказов: ${sumAllOrders}</p>
<p>клиент с максимальной суммой заказов: id: ${maxCustomer.id}, Имя: ${maxCustomer.name}</p>
<p>сумма максимального заказа: ${maxOrderCost}</p>
<p>сумма минимального заказа: ${minOrderCost}</p>
<p>количество заказов: ${ordersNumber}</p>
<p>средняя сумма заказов: ${avgOrderCost}</p>

<c:forEach items="${customers}" var="customer">
    <p>id: ${customer.id}, Имя: ${customer.name}, сумма заказов: ${customer.getTotalCost()}</p>
</c:forEach>

<a href="<c:url value="/"/>">Главная</a>
</body>
</html>
