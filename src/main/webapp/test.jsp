<!DOCTYPE html>
<html lang="en">

<head>
  <title></title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script defer src="https://unpkg.com/alpinejs@3.x.x/dist/cdn.min.js"></script>
</head>

<body>
  <div x-data="{ tab: 'description' }">
    <nav>
      <a :class="{ 'active': tab === 'description' }" x-on:click.prevent="tab = 'description'" href="#">Description</a>
      <a :class="{ 'active': tab === 'dimensions' }" x-on:click.prevent="tab = 'dimensions'" href="#">Dimensions</a>
      <a :class="{ 'active': tab === 'reviews' }" x-on:click.prevent="tab = 'reviews'" href="#">Reviews</a>
    </nav>
    <div x-show="tab === 'description'">
      <h3>Description</h3>
      <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed cursus
        facilisis tristique. Lorem ipsum dolor sit amet, consectetur adipiscing
        elit.
      </p>
    </div>
    <div x-show="tab === 'dimensions'">
      <h3>Dimensions</h3>
      <p>
        Donec placerat ullamcorper sodales. Nam congue justo sit amet erat luctus
        faucibus.
      </p>
    </div>
    <div x-show="tab === 'reviews'">
      <h3>Reviews</h3>
      <p>
        Sed hendrerit imperdiet accumsan. Nunc venenatis sit amet diam vel rutrum.
        Quisque interdum dui et molestie tristique.
      </p>
    </div>
  </div>
</body>

</html>
