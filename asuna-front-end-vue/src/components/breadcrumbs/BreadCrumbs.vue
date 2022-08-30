<template>
  <div class="row mt-3 mb-3">
    <div class="col-1">&nbsp;</div>
    <div class="col-10">
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb mt-3 mb-3">
          <li
              v-for="(breadcrumb, idx) in breadcrumbList"
              :key="idx" :class="{'linked breadcrumb-item active': !!breadcrumb.link}"
              @click="routeTo(idx)"
          >
            {{ breadcrumb.name }}
          </li>
        </ol>
      </nav>
    </div>
    <div class="col-1">&nbsp;</div>
  </div>
</template>

<script>
export default {
  name: 'BreadCrumb',
  data() {
    return {
      breadcrumbList: []
    }
  },
  mounted() {
    this.updateList()
  },
  watch: {
    '$route'() {
      this.updateList()
    }
  },
  methods: {
    routeTo(pRouteTo) {
      if (this.breadcrumbList[pRouteTo].link) this.$router.push(this.breadcrumbList[pRouteTo].link)
    },
    updateList() {
      this.breadcrumbList = this.$route.meta.breadcrumb
    }
  }
}
</script>