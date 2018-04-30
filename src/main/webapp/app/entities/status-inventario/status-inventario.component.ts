import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { StatusInventario } from './status-inventario.model';
import { StatusInventarioService } from './status-inventario.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-status-inventario',
    templateUrl: './status-inventario.component.html'
})
export class StatusInventarioComponent implements OnInit, OnDestroy {
statusInventarios: StatusInventario[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private statusInventarioService: StatusInventarioService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.statusInventarioService.query().subscribe(
            (res: HttpResponse<StatusInventario[]>) => {
                this.statusInventarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInStatusInventarios();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: StatusInventario) {
        return item.id;
    }
    registerChangeInStatusInventarios() {
        this.eventSubscriber = this.eventManager.subscribe('statusInventarioListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
