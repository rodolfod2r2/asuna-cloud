<template>
  <nav aria-label="Main navigation" class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">
        <img id="logo" alt="Asuna" class="d-inline-block align-top" height="30" src="assets/asuna.svg" width="30">
      </a>
      <button id="navbarSideCollapse" aria-label="Toggle navigation" class="navbar-toggler p-0 border-0" type="button">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div id="navbarAsuna" class="navbar-collapse offcanvas-collapse">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a aria-current="page" class="nav-link active" href="#">Dashboard</a>
          </li>
          <li class="nav-item dropdown">
            <a aria-expanded="false" class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">Spring Data</a>
            <ul class="dropdown-menu">
              <li>
                <router-link class="dropdown-item" to="/data-jpa">JPA</router-link>
              </li>
              <li><a class="dropdown-item" href="#">Mongo &raquo;</a>
                <ul class="submenu dropdown-menu">
                  <li>
                    <router-link class="dropdown-item" to="/data-mongo">DB</router-link>
                  </li>
                  <li>
                    <router-link class="dropdown-item" to="/data-mongo-reactive">Reactive</router-link>
                  </li>
                  <li>
                    <router-link class="dropdown-item" to="/data-mongo-route">Router</router-link>
                  </li>
                </ul>
              </li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a aria-expanded="false" class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">Spring
              Kafka</a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">View List</a></li>
              <li><a class="dropdown-item" href="#">Create Item</a></li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a aria-expanded="false" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
               href="#">Cryptography</a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">Asymmetric</a></li>
              <li><a class="dropdown-item" href="#">Symmetric</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'AsunaNavBar'
}

document.addEventListener("DOMContentLoaded", function () {
  if (window.innerWidth < 992) {
    document.querySelectorAll('.navbar .dropdown').forEach(function (everydropdown) {
      everydropdown.addEventListener('hidden.bs.dropdown', function () {
        this.querySelectorAll('.submenu').forEach(function (everysubmenu) {
          everysubmenu.style.display = 'none';
        });
      })
    });

    document.querySelectorAll('.dropdown-menu a').forEach(function (element) {
      element.addEventListener('click', function (e) {
        let nextEl = this.nextElementSibling;
        if (nextEl && nextEl.classList.contains('submenu')) {
          e.preventDefault();
          if (nextEl.style.display === 'block') {
            nextEl.style.display = 'none';
          } else {
            nextEl.style.display = 'block';
          }

        }
      });
    })
  }
});
</script>

<style scoped>

@media all and (min-width: 992px) {
  .dropdown-menu li {
    position: relative;
  }

  .nav-item .submenu {
    display: none;
    position: absolute;
    left: 100%;
    top: -7px;
  }

  .nav-item .submenu-left {
    right: 100%;
    left: auto;
  }

  .dropdown-menu > li:hover {
    background-color: #f1f1f1
  }

  .dropdown-menu > li:hover > .submenu {
    display: block;
  }
}

@media (max-width: 991px) {
  .dropdown-menu .dropdown-menu {
    margin-left: 0.7rem;
    margin-right: 0.7rem;
    margin-bottom: .5rem;
  }
}
</style>