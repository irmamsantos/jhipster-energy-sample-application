/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVehicleModelDataComponent } from 'app/entities/energy-vehicle-model-data/energy-vehicle-model-data.component';
import { EnergyVehicleModelDataService } from 'app/entities/energy-vehicle-model-data/energy-vehicle-model-data.service';
import { EnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';

describe('Component Tests', () => {
    describe('EnergyVehicleModelData Management Component', () => {
        let comp: EnergyVehicleModelDataComponent;
        let fixture: ComponentFixture<EnergyVehicleModelDataComponent>;
        let service: EnergyVehicleModelDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVehicleModelDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyVehicleModelDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyVehicleModelDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyVehicleModelDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyVehicleModelData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyVehicleModelData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
