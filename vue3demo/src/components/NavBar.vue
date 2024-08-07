<script setup>

</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container">
      <router-link class="navbar-brand" :to="{name: 'home'}">人员记录</router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
          <router-link class="nav-link" aria-current="page" :to="{name: 'all_index'}">全部记录</router-link>
          <router-link class="nav-link" aria-current="page" :to="{name: 'add_index'}">添加人员</router-link>
          <router-link class="nav-link" aria-current="page" :to="{name: 'select_index'}">查询人员</router-link>
        </div>
      </div>
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            cpx
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">123</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">登出</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</template>

<style scoped>

</style>