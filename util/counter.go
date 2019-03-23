package util

import (
	"sync"
)

// AtomicCounter : Represents a counter type that can be incremented/decremented atomically by multiple goroutines.
type AtomicCounter struct {
	count *int32
	mux   sync.Mutex
}

// NewAtomicCounter returns a new instance of the AtomicCounter type.
func NewAtomicCounter(n int32) *AtomicCounter {
	return &AtomicCounter{count: &n}
}

// Value : Returns the current value of the counter.
func (c *AtomicCounter) Value() int32 {
	return *c.count
}

// Incr : Increments the value of the counter.
func (c *AtomicCounter) Incr(delta int32) int32 {
	c.mux.Lock()
	defer c.mux.Unlock()
	*c.count = *c.count + delta
	return *c.count
}

// IncrBy1 : Increments counter value by 1.
func (c *AtomicCounter) IncrBy1() int32 {
	return c.Incr(1)
}

// Decr : Decrements the value of the counter.
func (c *AtomicCounter) Decr(delta int32) int32 {
	c.mux.Lock()
	defer c.mux.Unlock()
	*c.count = *c.count - delta
	return *c.count
}

// DecrBy1 : Decrements counter value by 1.
func (c *AtomicCounter) DecrBy1() int32 {
	return c.Decr(1)
}

// Counter : Represents a non-atomic counter that can be incremented/decremented by only a single goroutine.
type Counter struct {
	count *int32
}

// NewCounter returns a new instance of the Counter type.
func NewCounter(n int32) *Counter {
	return &Counter{count: &n}
}

// Value : Returns the current value of the counter.
func (c *Counter) Value() int32 {
	return *c.count
}

// IncrBy1 : Increments counter value by 1.
func (c *Counter) IncrBy1() int32 {
	*c.count = *c.count + 1
	return *c.count
}

// DecrBy1 : Decrements counter value by 1.
func (c *Counter) DecrBy1() int32 {
	*c.count = *c.count - 1
	return *c.count
}
