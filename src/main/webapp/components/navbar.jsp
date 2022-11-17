<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <header>
    <nav class="bg-white border-gray-200 px-2 sm:px-4 py-2.5 rounded dark:bg-gray-900">
      <div class="container flex flex-wrap items-center justify-between mx-auto">
        <a href="https://flowbite.com/" class="flex items-center">
          <img class="h-10 mr-2 dark:bg-white dark:p-3 dark:rounded-lg" src="https://simplonline.co/static/sol-logo.png"
            alt="logo">
        </a>
        <div class="items-center justify-between hidden w-full md:flex md:w-auto " id="navbar-cta">
          <ul
            class="flex flex-col p-4 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
            <li>${requestScope.role}</li>
          </ul>
        </div>
        <div class="flex gap-2 items-center ">
          <p>${requestScope.username}</p>
          <a href="logout"
            class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-3 md:mr-0 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Logout</a>
        </div>
      </div>
    </nav>
  </header>
