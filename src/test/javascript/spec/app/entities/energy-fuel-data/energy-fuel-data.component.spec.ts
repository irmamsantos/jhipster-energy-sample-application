/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyFuelDataComponent } from 'app/entities/energy-fuel-data/energy-fuel-data.component';
import { EnergyFuelDataService } from 'app/entities/energy-fuel-data/energy-fuel-data.service';
import { EnergyFuelData } from 'app/shared/model/energy-fuel-data.model';

describe('Component Tests', () => {
    describe('EnergyFuelData Management Component', () => {
        let comp: EnergyFuelDataComponent;
        let fixture: ComponentFixture<EnergyFuelDataComponent>;
        let service: EnergyFuelDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyFuelDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyFuelDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyFuelDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyFuelDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyFuelData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyFuelData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
