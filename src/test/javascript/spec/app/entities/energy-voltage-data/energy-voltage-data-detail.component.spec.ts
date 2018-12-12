/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVoltageDataDetailComponent } from 'app/entities/energy-voltage-data/energy-voltage-data-detail.component';
import { EnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';

describe('Component Tests', () => {
    describe('EnergyVoltageData Management Detail Component', () => {
        let comp: EnergyVoltageDataDetailComponent;
        let fixture: ComponentFixture<EnergyVoltageDataDetailComponent>;
        const route = ({ data: of({ energyVoltageData: new EnergyVoltageData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVoltageDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyVoltageDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyVoltageDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyVoltageData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
