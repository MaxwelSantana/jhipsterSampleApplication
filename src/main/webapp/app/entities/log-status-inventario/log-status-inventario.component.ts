import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LogStatusInventario } from './log-status-inventario.model';
import { LogStatusInventarioService } from './log-status-inventario.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-log-status-inventario',
    templateUrl: './log-status-inventario.component.html'
})
export class LogStatusInventarioComponent implements OnInit, OnDestroy {
logStatusInventarios: LogStatusInventario[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private logStatusInventarioService: LogStatusInventarioService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.logStatusInventarioService.query().subscribe(
            (res: HttpResponse<LogStatusInventario[]>) => {
                this.logStatusInventarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInLogStatusInventarios();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: LogStatusInventario) {
        return item.id;
    }
    registerChangeInLogStatusInventarios() {
        this.eventSubscriber = this.eventManager.subscribe('logStatusInventarioListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
