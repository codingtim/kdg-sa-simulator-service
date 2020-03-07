let app = new Vue({
    el: '#app',
    data: {
        snapshot: {},
        simulationConfiguration: {
            "duration": "PT15M",
            "delay": 2000,
            "delayVariation": 150,
            "delayType": "REALTIME",
            "locationConfiguration": {
                "latitudeConfiguration": {
                    "lowerBound": 51.2012806,
                    "upperBoundExclusive": 51.2064643
                },
                "longitudeConfiguration": {
                    "lowerBound": 4.5827843,
                    "upperBoundExclusive": 4.6048775
                }
            },
            "sensorConfigurations": [
                {
                    "type": "CO2",
                    "lowerBound": 0,
                    "upperBoundExclusive": 150
                },
                {
                    "type": "NOx",
                    "lowerBound": 0,
                    "upperBoundExclusive": 75
                }
            ]
        }
    },
    methods : {
        startSimulation : function(event){
            axios
                .post('../api/sensor-simulations', this.simulationConfiguration)
                .then(response => this.loadSimulations());
            event.preventDefault();
        },
        loadSimulations: function() {
            axios
                .get('../api/sensor-simulations')
                .then(response => (this.snapshot = response.data))
        }
    },
    mounted() {
        this.loadSimulations();
    }
});