<%@ page pageEncoding="UTF-8" %>
<c:if test="${items.pageCount > 1}">
    <div class="pagination-div">
        <ul class="pagination">
            <c:forEach var="page" begin="1" end="${items.pageCount}" >
                <c:choose>
                    <c:when test="${page == items.page}">
                        <li><a class="current-page" href="<c:url value="main?command=${command}&page=${page}"/>">${page}</a></li>
                    </c:when>
                    <c:when test="${page != items.page}">
                        <li><a href="<c:url value="main?command=${command}&page=${page}"/>">${page}</a></li>
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</c:if>
