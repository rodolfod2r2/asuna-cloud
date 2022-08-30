<template>
  <div class="row mb-3">
    <div class="col-1">&nbsp;</div>
    <div class="col-10">
      <div class="card">
        <div class="card-header">Form</div>
        <div class="card-body">
          <div class="row">
            <div class="col-12 mb-3">
              <input aria-label="message" class="form-control" placeholder="message" type="text">
            </div>
          </div>
          <div class="row">
            <div class="col-12">
              <button class="btn btn-sm btn-primary" @click="postData">Send</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-1">&nbsp;</div>
  </div>
  <div class="row mb-3">
    <div class="col-1">&nbsp;</div>
    <div class="col-10">
      <div class="card">
        <div class="card-header">
          <div>
            <div class="card-title">View List Message</div>
          </div>
        </div>
        <div class="card-body">
          <table class="table table-hover">
            <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">MESSAGE</th>
              <th scope="col">OPTIONS</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="result in results" v-bind:key="result.id">
              <th scope="row">{{ result.id }}</th>
              <td>{{ result.message }}</td>
              <td>DETAILS | EDIT | REMOVE</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <div class="col-1">&nbsp;</div>
  </div>
</template>

<script>
import apiClient from "@/configurations/apiClient";

export default {
  name: 'MongoReactive',
  data() {
    return {
      results: {}
    }
  },
  created() {
    this.getAllMessage()
  },
  methods: {
    getAllMessage() {
      apiClient
          .get("/data-mongo-reactive/message")
          .then((res) => {
            this.results = res.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    getByIdMessage(id) {
      apiClient
          .get(`/data-mongo-reactive/message/${id}`)
          .then((res) => {
            this.results = res.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    postMessage() {
      apiClient
          .post("/data-mongo-reactive/message",
              {
                message: ''
              }
          )
          .then(() => {
            console.log('message')
          })
          .catch((error) => {
            console.log(error);
          });
    },
    updateMessage(id) {
      apiClient
          .put(`/data-mongo-reactive/message/${id}`,
              {
                message: ''
              }
          )
          .then(() => {
            console.log('message')
          })
          .catch((error) => {
            console.log(error);
          });
    },
    deleteMessage(id) {
      apiClient
          .delete(`/data-mongo-reactive/message/${id}`)
          .then(() => {
            console.log('message')
          })
          .catch((error) => {
            console.log(error);
          });
    }
  }
}
</script>

<style scoped>

</style>


