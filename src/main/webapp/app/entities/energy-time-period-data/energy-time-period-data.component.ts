import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';
import { Principal } from 'app/core';
import { EnergyTimePeriodDataService } from './energy-time-period-data.service';

@Component({
    selector: 'jhi-energy-time-period-data',
    templateUrl: './energy-time-period-data.component.html'
})
export class EnergyTimePeriodDataComponent implements OnInit, OnDestroy {
    energyTimePeriodData: IEnergyTimePeriodData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyTimePeriodDataService: EnergyTimePeriodDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyTimePeriodDataService.query().subscribe(
            (res: HttpResponse<IEnergyTimePeriodData[]>) => {
                this.energyTimePeriodData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyTimePeriodData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyTimePeriodData) {
        return item.id;
    }

    registerChangeInEnergyTimePeriodData() {
        this.eventSubscriber = this.eventManager.subscribe('energyTimePeriodDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
