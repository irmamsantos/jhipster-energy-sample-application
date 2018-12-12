/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNaturalGasDataComponent } from 'app/entities/energy-natural-gas-data/energy-natural-gas-data.component';
import { EnergyNaturalGasDataService } from 'app/entities/energy-natural-gas-data/energy-natural-gas-data.service';
import { EnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';

describe('Component Tests', () => {
    describe('EnergyNaturalGasData Management Component', () => {
        let comp: EnergyNaturalGasDataComponent;
        let fixture: ComponentFixture<EnergyNaturalGasDataComponent>;
        let service: EnergyNaturalGasDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNaturalGasDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyNaturalGasDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyNaturalGasDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyNaturalGasDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyNaturalGasData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyNaturalGasData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
