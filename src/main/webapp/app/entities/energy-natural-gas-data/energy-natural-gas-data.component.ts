import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';
import { Principal } from 'app/core';
import { EnergyNaturalGasDataService } from './energy-natural-gas-data.service';

@Component({
    selector: 'jhi-energy-natural-gas-data',
    templateUrl: './energy-natural-gas-data.component.html'
})
export class EnergyNaturalGasDataComponent implements OnInit, OnDestroy {
    energyNaturalGasData: IEnergyNaturalGasData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private energyNaturalGasDataService: EnergyNaturalGasDataService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.energyNaturalGasDataService.query().subscribe(
            (res: HttpResponse<IEnergyNaturalGasData[]>) => {
                this.energyNaturalGasData = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEnergyNaturalGasData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEnergyNaturalGasData) {
        return item.id;
    }

    registerChangeInEnergyNaturalGasData() {
        this.eventSubscriber = this.eventManager.subscribe('energyNaturalGasDataListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
