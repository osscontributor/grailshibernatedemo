
<%@ page import="com.demo.Car" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Car List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Car</g:link></span>
        </div>
        <div class="body">
            <h1>Car List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="make" title="Make" />
                        
                   	        <g:sortableColumn property="model" title="Model" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${carInstanceList}" status="i" var="carInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${carInstance.id}">${fieldValue(bean:carInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:carInstance, field:'make')}</td>
                        
                            <td>${fieldValue(bean:carInstance, field:'model')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${carInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
