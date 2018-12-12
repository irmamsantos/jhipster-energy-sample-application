import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserSAC } from 'app/shared/model/user-sac.model';
import { UserSACService } from './user-sac.service';
import { UserSACComponent } from './user-sac.component';
import { UserSACDetailComponent } from './user-sac-detail.component';
import { UserSACUpdateComponent } from './user-sac-update.component';
import { UserSACDeletePopupComponent } from './user-sac-delete-dialog.component';
import { IUserSAC } from 'app/shared/model/user-sac.model';

@Injectable({ providedIn: 'root' })
export class UserSACResolve implements Resolve<IUserSAC> {
    constructor(private service: UserSACService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<UserSAC> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<UserSAC>) => response.ok),
                map((userSAC: HttpResponse<UserSAC>) => userSAC.body)
            );
        }
        return of(new UserSAC());
    }
}

export const userSACRoute: Routes = [
    {
        path: 'user-sac',
        component: UserSACComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterEnergySampleApplicationApp.userSAC.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-sac/:id/view',
        component: UserSACDetailComponent,
        resolve: {
            userSAC: UserSACResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.userSAC.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-sac/new',
        component: UserSACUpdateComponent,
        resolve: {
            userSAC: UserSACResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.userSAC.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-sac/:id/edit',
        component: UserSACUpdateComponent,
        resolve: {
            userSAC: UserSACResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.userSAC.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userSACPopupRoute: Routes = [
    {
        path: 'user-sac/:id/delete',
        component: UserSACDeletePopupComponent,
        resolve: {
            userSAC: UserSACResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.userSAC.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
