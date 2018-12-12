/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyElectricityDataDetailComponent } from 'app/entities/energy-electricity-data/energy-electricity-data-detail.component';
import { EnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';

describe('Component Tests', () => {
    describe('EnergyElectricityData Management Detail Component', () => {
        let comp: EnergyElectricityDataDetailComponent;
        let fixture: ComponentFixture<EnergyElectricityDataDetailComponent>;
        const route = ({ data: of({ energyElectricityData: new EnergyElectricityData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyElectricityDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyElectricityDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyElectricityDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyElectricityData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
