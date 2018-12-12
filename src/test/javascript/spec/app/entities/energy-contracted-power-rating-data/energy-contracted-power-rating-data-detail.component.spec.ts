/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyContractedPowerRatingDataDetailComponent } from 'app/entities/energy-contracted-power-rating-data/energy-contracted-power-rating-data-detail.component';
import { EnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';

describe('Component Tests', () => {
    describe('EnergyContractedPowerRatingData Management Detail Component', () => {
        let comp: EnergyContractedPowerRatingDataDetailComponent;
        let fixture: ComponentFixture<EnergyContractedPowerRatingDataDetailComponent>;
        const route = ({
            data: of({ energyContractedPowerRatingData: new EnergyContractedPowerRatingData(123) })
        } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyContractedPowerRatingDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyContractedPowerRatingDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyContractedPowerRatingDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyContractedPowerRatingData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
