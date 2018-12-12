/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNaturalGasDataDetailComponent } from 'app/entities/energy-natural-gas-data/energy-natural-gas-data-detail.component';
import { EnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';

describe('Component Tests', () => {
    describe('EnergyNaturalGasData Management Detail Component', () => {
        let comp: EnergyNaturalGasDataDetailComponent;
        let fixture: ComponentFixture<EnergyNaturalGasDataDetailComponent>;
        const route = ({ data: of({ energyNaturalGasData: new EnergyNaturalGasData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNaturalGasDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyNaturalGasDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyNaturalGasDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyNaturalGasData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
