import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { StatusInventario } from './status-inventario.model';
import { StatusInventarioService } from './status-inventario.service';

@Component({
    selector: 'jhi-status-inventario-detail',
    templateUrl: './status-inventario-detail.component.html'
})
export class StatusInventarioDetailComponent implements OnInit, OnDestroy {

    statusInventario: StatusInventario;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private statusInventarioService: StatusInventarioService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInStatusInventarios();
    }

    load(id) {
        this.statusInventarioService.find(id)
            .subscribe((statusInventarioResponse: HttpResponse<StatusInventario>) => {
                this.statusInventario = statusInventarioResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInStatusInventarios() {
        this.eventSubscriber = this.eventManager.subscribe(
            'statusInventarioListModification',
            (response) => this.load(this.statusInventario.id)
        );
    }
}
