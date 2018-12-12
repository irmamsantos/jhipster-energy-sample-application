/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { UserSACDetailComponent } from 'app/entities/user-sac/user-sac-detail.component';
import { UserSAC } from 'app/shared/model/user-sac.model';

describe('Component Tests', () => {
    describe('UserSAC Management Detail Component', () => {
        let comp: UserSACDetailComponent;
        let fixture: ComponentFixture<UserSACDetailComponent>;
        const route = ({ data: of({ userSAC: new UserSAC(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [UserSACDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UserSACDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UserSACDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.userSAC).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
