/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVehicleBrandDataDetailComponent } from 'app/entities/energy-vehicle-brand-data/energy-vehicle-brand-data-detail.component';
import { EnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';

describe('Component Tests', () => {
    describe('EnergyVehicleBrandData Management Detail Component', () => {
        let comp: EnergyVehicleBrandDataDetailComponent;
        let fixture: ComponentFixture<EnergyVehicleBrandDataDetailComponent>;
        const route = ({ data: of({ energyVehicleBrandData: new EnergyVehicleBrandData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVehicleBrandDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyVehicleBrandDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyVehicleBrandDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyVehicleBrandData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
