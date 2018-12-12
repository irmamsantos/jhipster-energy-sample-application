/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyContractedPowerRatingDataComponent } from 'app/entities/energy-contracted-power-rating-data/energy-contracted-power-rating-data.component';
import { EnergyContractedPowerRatingDataService } from 'app/entities/energy-contracted-power-rating-data/energy-contracted-power-rating-data.service';
import { EnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';

describe('Component Tests', () => {
    describe('EnergyContractedPowerRatingData Management Component', () => {
        let comp: EnergyContractedPowerRatingDataComponent;
        let fixture: ComponentFixture<EnergyContractedPowerRatingDataComponent>;
        let service: EnergyContractedPowerRatingDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyContractedPowerRatingDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyContractedPowerRatingDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyContractedPowerRatingDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyContractedPowerRatingDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyContractedPowerRatingData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyContractedPowerRatingData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
