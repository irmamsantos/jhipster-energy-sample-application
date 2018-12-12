import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';
import { Principal } from 'app/core';
import { EnergyVoltageDataService } from './energy-voltage-data.service';

@Component({
    selector: 'jhi-energy-voltage-data',
    templateUrl: './energy-voltage-data.component.html'
})
export class EnergyVoltageDataComponent implements OnInit, OnDestroy {
    energyVoltageData: IEnergyVoltageData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyVoltageDataService: EnergyVoltageDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyVoltageDataService.query().subscribe(
            (res: HttpResponse<IEnergyVoltageData[]>) => {
                this.energyVoltageData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyVoltageData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyVoltageData) {
        return item.id;
    }

    registerChangeInEnergyVoltageData() {
        this.eventSubscriber = this.eventManager.subscribe('energyVoltageDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
