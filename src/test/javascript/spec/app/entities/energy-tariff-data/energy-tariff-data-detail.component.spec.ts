/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyTariffDataDetailComponent } from 'app/entities/energy-tariff-data/energy-tariff-data-detail.component';
import { EnergyTariffData } from 'app/shared/model/energy-tariff-data.model';

describe('Component Tests', () => {
    describe('EnergyTariffData Management Detail Component', () => {
        let comp: EnergyTariffDataDetailComponent;
        let fixture: ComponentFixture<EnergyTariffDataDetailComponent>;
        const route = ({ data: of({ energyTariffData: new EnergyTariffData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyTariffDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyTariffDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyTariffDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyTariffData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
