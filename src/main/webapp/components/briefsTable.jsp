<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <div x-data="{showAssign: false, showAdd: false, currId:0}">
    <div class="flex items-center w-full justify-between my-4">
      <h3 class="text-2xl font-bold py-4 ">Briefs:</h3>
      <c:if test="${role == 'instructor'}">
        <button @click="showAdd = true" type="button"
          class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">add
          new brief</button>
      </c:if>
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
                <c:if test="${role == 'instructor'}">
                  <th scope="col" class="py-3 px-6"> Action </th>
                </c:if>
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
                  <c:if test="${role == 'instructor' }">
                    <td class=" py-4 px-6 space-x-2">
                      <c:choose>
                        <c:when test="${brief.getPromo() == null}">
                          <button @click="() => {showAssign = true ; currId = '${brief.getId()}'; }"
                            class="font-bold text-emerald-600  hover:underline">Assign</a>
                        </c:when>
                        <c:otherwise>
                          <button @click="() => {showAssign = true ; currId = '${brief.getId()}'; }"
                            class="font-bold text-lime-500  hover:underline">Rassign</a>
                        </c:otherwise>
                      </c:choose>
                    </td>
                  </c:if>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </c:otherwise>
    </c:choose>

    <!-- add modal -->
    <div x-show="showAdd" tabindex="-1"
      class=" overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-modal md:h-full flex bg-gray-700 bg-opacity-50">
      <div class="relative p-4 w-full max-w-md h-full md:h-auto">
        <!-- Modal content -->
        <div class="relative p-4 bg-white rounded-lg shadow dark:bg-gray-800 sm:p-5">
          <h3 class="mb-4 text-xl font-medium text-gray-900 dark:text-white">add new brief:</h3>
          <form class="space-y-6" action="#" method="post">
            <div>
              <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"> Title:</label>
              <input type="text" name="title" id="title"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
                placeholder="Jhon doe" required>
            </div>
            <div>
              <label for="content"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Content:</label>
              <textarea id="content" name="content" rows="4"
                class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="Write your thoughts here..."></textarea>
            </div>
            <div date-rangepicker datepicker-format="yyyy-mm-dd" class="flex items-center">
              <div class="relative">
                <div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
                  <svg aria-hidden="true" class="w-5 h-5 text-gray-500 dark:text-gray-400" fill="currentColor"
                    viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                      d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z"
                      clip-rule="evenodd"></path>
                  </svg>
                </div>
                <input name="launchDate" type="text"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Select launch date ">
              </div>
              <span class="mx-4 text-gray-500">to</span>
              <div class="relative">
                <div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
                  <svg aria-hidden="true" class="w-5 h-5 text-gray-500 dark:text-gray-400" fill="currentColor"
                    viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                      d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z"
                      clip-rule="evenodd"></path>
                  </svg>
                </div>
                <input name="deadline" type="text"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Select deadline">
              </div>
            </div>

            <div class="flex justify-center items-center space-x-4">
              <button type="button" @click="showAdd = false"
                class="py-2 px-3 text-sm font-medium text-gray-500 bg-white rounded-lg border border-gray-200 hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-primary-300 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">
                cancel </button>
              <button type="submit"
                class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                Save </button>
            </div>
            <input type="hidden" name="action" value="addBrief">
          </form>
        </div>
      </div>
    </div>

    <!-- assign modal -->
    <div x-show="showAssign" tabindex="-1"
      class="overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-modal md:h-full flex bg-gray-700 bg-opacity-50">
      <div class="relative p-4 w-full max-w-md h-full md:h-auto">
        <!-- Modal content -->
        <div class="relative p-4 bg-white rounded-lg shadow dark:bg-gray-800 sm:p-5">
          <h3 class="mb-4 text-xl font-medium text-gray-900 dark:text-white">assign brief to promo:</h3>
          <form class="space-y-6" action="#" method="post">
            <div>
              <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Promo
                Name:</label>
              <select name="promoId" id="name"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
                placeholder="select promo">
                <c:forEach items="${promos}" var="promo">
                  <option value="${promo.getId()}">${promo.getName()}</option>
                </c:forEach>
              </select>
            </div>
            <div class="flex justify-center items-center space-x-4">
              <button type="button" @click="showAssign = false"
                class="py-2 px-3 text-sm font-medium text-gray-500 bg-white rounded-lg border border-gray-200 hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-primary-300 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">
                cancel </button>
              <button type="submit"
                class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                Save </button>
            </div>
            <input type="hidden" name="briefId" :value="currId">
            <input type="hidden" name="action" value="assignBrief">
          </form>
        </div>
      </div>
    </div>
  </div>
  <script src="https://unpkg.com/flowbite@1.5.4/dist/datepicker.js"></script>
