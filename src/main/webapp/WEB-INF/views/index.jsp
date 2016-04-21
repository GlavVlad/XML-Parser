<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>

<div>
    <form method="POST" enctype="multipart/form-data" action="<c:url value="/upload"/>">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input accept=".xml" type="file" name="file"/></td>
            </tr>
            <tr>
                <td>Сумма заказов ></td>
                <td><input type="number" name="cost" value="0"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"/></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>