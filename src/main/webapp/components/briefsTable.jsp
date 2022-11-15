<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <div class="flex items-center w-full justify-between my-4">
    <h3 class="text-2xl font-bold py-4 ">Briefs:</h3>
  </div>
  <c:choose>
    <c:when test="${briefs.size() == 0 || briefs == null}">
      <p classs="text-gray-200 italic">no briefs found!</p>
    </c:when>
    <c:otherwise>
      <div class="overflow-x-auto relative shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" class="py-3 px-6"> Title </th>
              <th scope="col" class="py-3 px-6"> LaunchDate </th>
              <th scope="col" class="py-3 px-6"> Deadline </th>
              <th scope="col" class="py-3 px-6"> Promo </th>
              <th scope="col" class="py-3 px-6"> Action </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${briefs}" var="brief">
              <tr
                class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <th scope="row" class="flex items-center py-4 px-6 text-gray-900 whitespace-nowrap dark:text-white">
                  <div class="pl-3">
                    <div class="text-base font-semibold">${brief.getTitle()}</div>
                  </div>
                </th>
                <td class="py-4 px-6"> ${brief.getLaunchDate()} </td>
                <td class="py-4 px-6"> ${brief.getDeadline()} </td>
                <td class="py-4 px-6">
                  <c:choose>
                    <c:when test="${brief.getPromo() == null}">
                      <span class="text-gray-400 italic">not assigned</span>
                    </c:when>
                    <c:otherwise>
                      <div>${brief.getPromo().getName()}</div>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td class=" py-4 px-6 space-x-2">
                  <!-- Modal toggle -->
                  <a href="#" type="button" data-modal-toggle="editUserModal"
                    class="font-bold text-blue-600 dark:text-blue-500 hover:underline">Edit</a>
                  <a href="#" type="button" data-modal-toggle="delelteUserModal"
                    class="font-bold text-red-300 eark:text-red-300 hover:underline">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>
