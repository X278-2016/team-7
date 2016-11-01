(function() {
    'use strict';
    angular
        .module('team7App')
        .factory('PadSet', PadSet);

    PadSet.$inject = ['$resource'];

    function PadSet ($resource) {
        var resourceUrl =  'api/pad-sets/:id';

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
