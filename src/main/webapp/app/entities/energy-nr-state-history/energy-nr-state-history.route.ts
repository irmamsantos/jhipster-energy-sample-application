import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EnergyNRStateHistory } from 'app/shared/model/energy-nr-state-history.model';
import { EnergyNRStateHistoryService } from './energy-nr-state-history.service';
import { EnergyNRStateHistoryComponent } from './energy-nr-state-history.component';
import { EnergyNRStateHistoryDetailComponent } from './energy-nr-state-history-detail.component';
import { EnergyNRStateHistoryUpdateComponent } from './energy-nr-state-history-update.component';
import { EnergyNRStateHistoryDeletePopupComponent } from './energy-nr-state-history-delete-dialog.component';
import { IEnergyNRStateHistory } from 'app/shared/model/energy-nr-state-history.model';

@Injectable({ providedIn: 'root' })
export class EnergyNRStateHistoryResolve implements Resolve<IEnergyNRStateHistory> {
    constructor(private service: EnergyNRStateHistoryService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EnergyNRStateHistory> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EnergyNRStateHistory>) => response.ok),
                map((energyNRStateHistory: HttpResponse<EnergyNRStateHistory>) => energyNRStateHistory.body)
            );
        }
        return of(new EnergyNRStateHistory());
    }
}

export const energyNRStateHistoryRoute: Routes = [
    {
        path: 'energy-nr-state-history',
        component: EnergyNRStateHistoryComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNRStateHistory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-nr-state-history/:id/view',
        component: EnergyNRStateHistoryDetailComponent,
        resolve: {
            energyNRStateHistory: EnergyNRStateHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNRStateHistory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-nr-state-history/new',
        component: EnergyNRStateHistoryUpdateComponent,
        resolve: {
            energyNRStateHistory: EnergyNRStateHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNRStateHistory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'energy-nr-state-history/:id/edit',
        component: EnergyNRStateHistoryUpdateComponent,
        resolve: {
            energyNRStateHistory: EnergyNRStateHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNRStateHistory.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const energyNRStateHistoryPopupRoute: Routes = [
    {
        path: 'energy-nr-state-history/:id/delete',
        component: EnergyNRStateHistoryDeletePopupComponent,
        resolve: {
            energyNRStateHistory: EnergyNRStateHistoryResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterEnergySampleApplicationApp.energyNRStateHistory.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
