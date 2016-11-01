(function() {
    'use strict';
    angular
        .module('team7App')
        .factory('Threshold', Threshold);

    Threshold.$inject = ['$resource'];

    function Threshold ($resource) {
        var resourceUrl =  'api/thresholds/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
