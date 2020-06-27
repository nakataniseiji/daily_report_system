<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>従業員一覧</h2>
        <table id="employee_list">
            <tbody>
                <tr>
                    <th>社員番号</th>
                    <th>氏名</th>
                    <th>Follow</th>
                </tr>
                <c:set var="login_employee" value="${login_employee}" />
                <c:forEach var="employee" items="${employees}" varStatus="status">
                    <c:if test="${employee.id != login_employee.id}">
                        <tr class="row${status.count % 2}">
                            <td><c:out value="${employee.code}" /></td>
                            <td><c:out value="${employee.name}" /></td>
                            <td>
                                   <c:set var="um" value="0" />
                                <c:forEach var="follow" items="${follows}" varStatus="status">
                                    <c:if test="${follow.follow.id == employee.id}">
                                        <c:set var="um" value="1" />
                                    </c:if>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${um == 0}">
                                        <form method="post" action="<c:url value='/follower/update' />">
                                            <input type="hidden" name="id" value="${employee.id}" />
                                            <input type="submit" name="follow"  value="follow" />
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form method="post" action="<c:url value='/follower/destroy' />">
                                            <input type="hidden" name="id" value="${employee.id}" />
                                            <input type="submit" name="unfollow" value="unfollow" />
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${employee_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((employee_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/follower/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>