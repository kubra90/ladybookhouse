<template>
    <div class="position-fixed bottom-0 end-0 p-3" style="z-index:11" >
    <div v-for="toast in toasts" :key="toast.id" class="toast show" role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="5000">
    <div class="toast-header">
        <strong class="me-auto"> {{ toast.title }}</strong>
        <button type="button" class="btn-close" @click="removeToast(toast.id)" aria-label="Close"></button>
    </div>

    <div class="toast-body">
       {{ toast.message }}
    </div>
    </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            toasts: [],
        }
    },
    methods: {
        addToast(toast) {
            const id =Date.now();
            this.toasts.push({...toast, id});
            setTimeout(()=> this.removeToast(id), 5000) // Automatically remove toast after 5 seconds
        },

        removeToast(id) {
            this.toasts = this.toasts.filter(toast => toast.id !== id);
        }
    }
}
</script>