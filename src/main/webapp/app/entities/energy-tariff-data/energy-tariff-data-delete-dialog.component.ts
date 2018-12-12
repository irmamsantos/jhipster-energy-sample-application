import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyTariffData } from 'app/shared/model/energy-tariff-data.model';
import { EnergyTariffDataService } from './energy-tariff-data.service';

@Component({
    selector: 'jhi-energy-tariff-data-delete-dialog',
    templateUrl: './energy-tariff-data-delete-dialog.component.html'
})
export class EnergyTariffDataDeleteDialogComponent {
    energyTariffData: IEnergyTariffData;

    constructor(
        private energyTariffDataService: EnergyTariffDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyTariffDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyTariffDataListModification',
                content: 'Deleted an energyTariffData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-tariff-data-delete-popup',
    template: ''
})
export class EnergyTariffDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyTariffData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyTariffDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyTariffData = energyTariffData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
