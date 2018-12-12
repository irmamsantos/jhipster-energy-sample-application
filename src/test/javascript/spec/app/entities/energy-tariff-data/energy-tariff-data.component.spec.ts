/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyTariffDataComponent } from 'app/entities/energy-tariff-data/energy-tariff-data.component';
import { EnergyTariffDataService } from 'app/entities/energy-tariff-data/energy-tariff-data.service';
import { EnergyTariffData } from 'app/shared/model/energy-tariff-data.model';

describe('Component Tests', () => {
    describe('EnergyTariffData Management Component', () => {
        let comp: EnergyTariffDataComponent;
        let fixture: ComponentFixture<EnergyTariffDataComponent>;
        let service: EnergyTariffDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyTariffDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyTariffDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyTariffDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyTariffDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyTariffData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyTariffData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
