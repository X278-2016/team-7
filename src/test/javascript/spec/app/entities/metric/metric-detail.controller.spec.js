'use strict';

describe('Controller Tests', function() {

    describe('Metric Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockMetric, MockEquipmentSet, MockThreshold;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockMetric = jasmine.createSpy('MockMetric');
            MockEquipmentSet = jasmine.createSpy('MockEquipmentSet');
            MockThreshold = jasmine.createSpy('MockThreshold');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Metric': MockMetric,
                'EquipmentSet': MockEquipmentSet,
                'Threshold': MockThreshold
            };
            createController = function() {
                $injector.get('$controller')("MetricDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'team7App:metricUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
