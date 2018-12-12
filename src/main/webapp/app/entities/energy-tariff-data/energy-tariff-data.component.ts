import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyTariffData } from 'app/shared/model/energy-tariff-data.model';
import { Principal } from 'app/core';
import { EnergyTariffDataService } from './energy-tariff-data.service';

@Component({
    selector: 'jhi-energy-tariff-data',
    templateUrl: './energy-tariff-data.component.html'
})
export class EnergyTariffDataComponent implements OnInit, OnDestroy {
    energyTariffData: IEnergyTariffData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyTariffDataService: EnergyTariffDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyTariffDataService.query().subscribe(
            (res: HttpResponse<IEnergyTariffData[]>) => {
                this.energyTariffData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyTariffData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyTariffData) {
        return item.id;
    }

    registerChangeInEnergyTariffData() {
        this.eventSubscriber = this.eventManager.subscribe('energyTariffDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
