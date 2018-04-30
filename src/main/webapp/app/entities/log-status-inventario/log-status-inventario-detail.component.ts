import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { LogStatusInventario } from './log-status-inventario.model';
import { LogStatusInventarioService } from './log-status-inventario.service';

@Component({
    selector: 'jhi-log-status-inventario-detail',
    templateUrl: './log-status-inventario-detail.component.html'
})
export class LogStatusInventarioDetailComponent implements OnInit, OnDestroy {

    logStatusInventario: LogStatusInventario;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private logStatusInventarioService: LogStatusInventarioService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLogStatusInventarios();
    }

    load(id) {
        this.logStatusInventarioService.find(id)
            .subscribe((logStatusInventarioResponse: HttpResponse<LogStatusInventario>) => {
                this.logStatusInventario = logStatusInventarioResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLogStatusInventarios() {
        this.eventSubscriber = this.eventManager.subscribe(
            'logStatusInventarioListModification',
            (response) => this.load(this.logStatusInventario.id)
        );
    }
}
