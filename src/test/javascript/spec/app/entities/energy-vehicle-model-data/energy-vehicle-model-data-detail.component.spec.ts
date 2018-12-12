/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVehicleModelDataDetailComponent } from 'app/entities/energy-vehicle-model-data/energy-vehicle-model-data-detail.component';
import { EnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';

describe('Component Tests', () => {
    describe('EnergyVehicleModelData Management Detail Component', () => {
        let comp: EnergyVehicleModelDataDetailComponent;
        let fixture: ComponentFixture<EnergyVehicleModelDataDetailComponent>;
        const route = ({ data: of({ energyVehicleModelData: new EnergyVehicleModelData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVehicleModelDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyVehicleModelDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyVehicleModelDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyVehicleModelData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
