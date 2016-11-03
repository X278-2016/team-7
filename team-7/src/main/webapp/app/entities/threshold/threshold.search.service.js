(function() {
    'use strict';

    angular
        .module('team7App')
        .factory('ThresholdSearch', ThresholdSearch);

    ThresholdSearch.$inject = ['$resource'];

    function ThresholdSearch($resource) {
        var resourceUrl =  'api/_search/thresholds/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
