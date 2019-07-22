let particles = [];

function setup() {
    createCanvas(600, 400);

    for (let i = 0; i < 100; i++) {
        particles[i] = new Particle(random(width), random(height));
    }
}

function draw() {
    background(0);
    for (let p of particles) {
        p.move();
        p.render();
        p.setHighlight(false);
    }

    for (let p of particles) {
        for (let other of particles) {
            if (p !== other && p.intersects(other)) {
                p.setHighlight(true);
            }
        }
    }
}
