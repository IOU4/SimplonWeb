<!DOCTYPE html>
<html lang="en">

<head>
  <title>Home - simlonline</title>
  <jsp:include page="/components/headers.jsp" />
  <script defer src="https://unpkg.com/alpinejs"></script>
</head>


<body class="px-3">
  <jsp:include page="/components/navbar.jsp" />
  <main
    x-data="{tab: 'students', active: 'inline-block p-4 text-blue-600 rounded-t-lg border-b-2 border-blue-600 active dark:text-blue-500 dark:border-blue-500', inactive:'inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300'}">
    <section>
      <jsp:include page="/components/studentsTable.jsp" />
    </section>
    <section>
      <jsp:include page="/components/briefsTable.jsp" />
    </section>
  </main>
  <jsp:include page="/components/footer.jsp" />
  <script src="https://unpkg.com/flowbite@1.5.3/dist/flowbite.js"></script>
</body>

</html>
