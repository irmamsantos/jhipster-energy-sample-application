/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyFuelDataDetailComponent } from 'app/entities/energy-fuel-data/energy-fuel-data-detail.component';
import { EnergyFuelData } from 'app/shared/model/energy-fuel-data.model';

describe('Component Tests', () => {
    describe('EnergyFuelData Management Detail Component', () => {
        let comp: EnergyFuelDataDetailComponent;
        let fixture: ComponentFixture<EnergyFuelDataDetailComponent>;
        const route = ({ data: of({ energyFuelData: new EnergyFuelData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyFuelDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyFuelDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyFuelDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyFuelData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
