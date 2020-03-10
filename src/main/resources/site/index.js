let app = new Vue({
    el: '#app',
    data: {
        snapshot: {},
        simulationConfiguration: {
            "locationConfiguration": {
                "latitudeConfiguration": {},
                "longitudeConfiguration": {}
            },
            "sensorConfigurations": []
        }
    },
    methods: {
        startSimulation: function (event) {
            axios
                .post('../api/sensor-simulations', this.simulationConfiguration)
                .then(response => this.loadSimulations());
            event.preventDefault();
        },
        loadSimulations: function () {
            axios
                .get('../api/sensor-simulations')
                .then(response => (this.snapshot = response.data))
        }
    },
    mounted() {
        axios
            .get('../api/sensor-simulations/default')
            .then(response => (this.simulationConfiguration = response.data))
        this.loadSimulations();
    }
});